'use strict';

var webpack   = require('webpack');
var path      = require('path');
var BUILD_DIR = path.resolve(__dirname, 'build');
var APP_DIR   = path.resolve(__dirname, 'app');

var config = {
    target: 'web',
    entry: APP_DIR + '/main.jsx',
//    resolve: {
//        alias: {},
//        root: APP_DIR,
//        extension: ['', '.js'],
//        modulesDirectories: ['node_modules', APP_DIR]
//    },
    output: {
        path: BUILD_DIR,
//        publicPath: '',
//        pathInfo: true,
        filename: 'index.js'
    },
    devServer: {
        inline: true,
        port: 8080
    },
    module: {
	    loaders: [{
	        test: /\.jsx?$/,
			exclude: /node_modules/,
			include: APP_DIR,
			loader: 'babel',
            query: {
                presets: ['es2015', 'react']
            }
        }]
	}//,
//    plugins: [
//        //new webpack.optimize.CommonsChunkPlugin('common', 'common.js'),
//        new webpack.optimize.UglifyJsPlugin({
//            compress: { warnings: false },
//            output: { comments: false }
//        }),
//        new webpack.NoErrorsPlugin()
//    ]
};

module.exports = config;
