/* eslint @typescript-eslint/no-var-requires: 0 */

import express from "express";
import compression from "compression";
import dotenv from "dotenv";
import webpack from "webpack";
import WebpackDevMiddleware from "webpack-dev-middleware";
import WebpackHotMiddleware from "webpack-hot-middleware";
import renderHtmlPage from "./renderHtmlPage";

dotenv.config();

const app = express();

// if (process.env.NODE_ENV === "development") {
//   const webpackConfig = require("../../webpack/webpack.dev.client.js");
//   const compiler = webpack(webpackConfig);
//
//   app.use(
//     WebpackDevMiddleware(compiler, {
//       publicPath: webpackConfig.output.publicPath,
//       serverSideRender: true,
//     })
//   );
//
//   app.use(WebpackHotMiddleware(compiler));
// }

// Gzip
app.use(compression());
app.use(express.static("dist"));

app.get("*", (req: express.Request, res: express.Response) => {
  
  try {
  
    renderHtmlPage(req, res);
  
  } catch (err) {
    
    console.error("error in rendering html on server side:", err);
    
  }
});

app.listen(4000, () => {
  console.log("Listening on port 4000");
});
