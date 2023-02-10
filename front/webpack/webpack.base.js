const {CleanWebpackPlugin} = require("clean-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const scriptExtensions = /\.(tsx|ts|js|jsx|mjs)$/;
const imageExtensions = /\.(bmp|gif|jpg|jpeg|png)$/;
const fontsExtension = /\.(eot|otf|ttf|woff|woff2)$/;

module.exports = {
    resolve: {
        extensions: ['.js', '.jsx', '.json', '.ts', '.tsx'],
    },
    module: {
        rules: [
            {
                test: scriptExtensions,
                exclude: /node_modules/,
                use: 'babel-loader',
            },
            {
                test: fontsExtension,
                type: 'asset',
            },
            {
                test: /\.svg/,
                type: 'asset/inline',
            },
            {
                test: imageExtensions,
                type: 'asset/resource',
            },
            {
                test: /\.(css|less|styl|scss|sass|sss)$/,
                use: [MiniCssExtractPlugin.loader, 'css-loader'],
            },
        ],
    },
    plugins: [
        new MiniCssExtractPlugin(),
    ]
};
