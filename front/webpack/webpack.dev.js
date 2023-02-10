const webpack = require('webpack');
const {merge} = require('webpack-merge');
const path = require('path');
const baseCfg = require('./webpack.base.js');
const {BUILD_DIR, resolvePath} = require('./paths');

const {WebpackManifestPlugin} = require("webpack-manifest-plugin");
const NodemonPlugin = require('nodemon-webpack-plugin');
const webpackNodeExternals = require("webpack-node-externals");
const {CleanWebpackPlugin} = require("clean-webpack-plugin");

// const sharedManifest = {files: [], entrypoints: []};

const clientCfg = {
    target: 'web',
    mode: 'development',
    entry: {
        client: [
            // 'webpack-hot-middleware/client?reload=true&noInfo=true',
            './src/client/index.tsx'
        ],
    },
    devtool: 'inline-cheap-module-source-map',
    output: {
        path: resolvePath(BUILD_DIR, 'client'),
        publicPath: '/client/',
        filename: '[name].js',
        chunkFilename: '[name].js',
        assetModuleFilename: 'assets/[hash][ext][query]',
    },
    plugins: [
        // new webpack.HotModuleReplacementPlugin(),
        new CleanWebpackPlugin({
            verbose: true,
            dry: false,
            dangerouslyAllowCleanPatternsOutsideProject: true,
            cleanStaleWebpackAssets: false,
            // cleanOnceBeforeBuildPatterns: ['**/*', '!/manifest-webpack.json'],
            // cleanAfterEveryBuildPatterns: ['**/*', '!dist/manifest-webpack.json']
        }),
        new WebpackManifestPlugin({
            fileName: 'manifest-client.json',
            // seed: sharedManifest,
            generate(seed, files, entrypoints) {

                console.log('cwd client: ', process.cwd());
                // files.forEach(file => {
                //     seed.files.push(file.name);
                // });
                // seed.entrypoints = seed.entrypoints.concat(entrypoints);

                return {
                    files: files.map(file => file.name),
                    entrypoints,
                };
            }
        })
    ],
    optimization: {
        runtimeChunk: 'single', // creates a runtime file to be shared for all generated chunks.
        splitChunks: {
            chunks: 'all', // This indicates which chunks will be selected for optimization.
            automaticNameDelimiter: '-',
            cacheGroups: {
                vendor: {
                    // to convert long vendor generated large name into vendor.js
                    test: /[\\/]node_modules[\\/]/,
                    name: 'vendor',
                    chunks: 'all',
                },
            },
        },
        minimize: false,
        minimizer: [],
    },
}

const serverCfg = {
    target: 'node',
    mode: 'development',
    name: 'server',
    entry: {
        server: './src/server/index.tsx',
    },
    plugins: [
        new WebpackManifestPlugin({
            fileName: 'manifest-server.json',
            // seed: sharedManifest,
            generate(seed, files, entrypoints) {
                console.log('cwd server: ', process.cwd());
                return {
                    files: files.map(file => file.name),
                    entrypoints,
                };
            }
        }),
        new NodemonPlugin({watch: BUILD_DIR})
    ],
    output: {
        path: resolvePath(BUILD_DIR, 'server'),
        filename: '[name].js',
        publicPath: '/server/',
        libraryTarget: 'commonjs2',
        chunkFilename: 'chunks/[name].js',
        assetModuleFilename: 'assets/[hash][ext][query]',
    },
    externals: [webpackNodeExternals()],
    // devtool: 'source-map',
}

module.exports = [merge(baseCfg, serverCfg), merge(baseCfg, clientCfg)]
