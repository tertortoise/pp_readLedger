const isProd = process.env.NODE_ENV === 'production';

// const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
    mode: isProd ? 'production' : 'development',
    entry: './src/index.tsx',
    target: ['browserslist'],
    output: {
        path: __dirname + '/build/',
    },
    module: {
        rules: [
            {
                test: /\.(ts|tsx)$/,
                exclude: /node_modules/,
                resolve: {
                    extensions: ['.ts', '.tsx', '.js', 'jsx', '.json'],
                },
                use: 'babel-loader',
            },
            {
                test: /\.css$/,
                use: [MiniCssExtractPlugin.loader, 'css-loader']
            },
            // wip
            // {
            //     test: /\.(?:ico|gif|png|jpg|jpeg)$/i,
            //     type: 'asset/resource',
            // },
            // {
            //     test: /\.(woff(2)?|eot|ttf|otf|svg|)$/,
            //     type: 'asset/inline',
            // },
        ]
    },
    optimization: {
        minimize: true,
        minimizer: [
            new TerserPlugin({
                terserOptions: {
                    compress: {}
                }
            })
        ]
    },
    devtool: isProd ? undefined : 'source-map',
    plugins: [
        // new HtmlWebpackPlugin({template: "public/index.html"}),
        new MiniCssExtractPlugin(),
    ]


}
