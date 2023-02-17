import express from "express";
import path from "path";
import {renderToString} from "react-dom/server";
import {StaticRouter} from "react-router-dom/server";
import createEmotionServer from '@emotion/server/create-instance';
import CssBaseline from '@mui/material/CssBaseline';
import {ThemeProvider} from '@mui/material/styles';
import {CacheProvider} from '@emotion/react';

import createEmotionCache from '../styles/cache';
import theme from "../styles/theme";
import App, {cache} from "../components/App";
import * as fs from "fs";

//https://mui.com/material-ui/guides/server-rendering/
const manifestPath = `${process.cwd()}/dist/client/manifest-client.json`;

// generate stylesheets and generate styletags

//read the manifest.json file
const manifest = fs.readFileSync(manifestPath, 'utf-8');
const manifestObj = JSON.parse(manifest);
const files = manifestObj.entrypoints.client as string[];

const cssRegex = /\.css$/i;
const scriptRegex = /\.js$/i;

let cssTags: string = '';
let scriptTags: string = '';

function genScriptTag(src: string) {
  
  return `<script async src="/client/${src}"></script>`
  
}

function genCssTag(href: string) {
  
  return `<link  rel="stylesheet" href="/client/${href}">`
  
}

files.forEach(file => {
  
  if (cssRegex.test(file)) {
    
    cssTags += genCssTag(file);
    
  } else if (scriptRegex.test(file)) {
    
    scriptTags += genScriptTag(file);
    
  }
})

function renderHtmlTemplate(reactContent: string, emotionCss: string) {
  
  return `
    <!DOCTYPE html>
    <html lang="en">
      <head>
        <title>My page</title>
        <link rel="icon" href="/images/favicon.ico" />
        <meta name="viewport" content="initial-scale=1, width=device-width" />
<!--        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />-->
        ${cssTags}
        <meta name="emotion-insertion-point" content="" />
        ${emotionCss}
      </head>
      <body>
      <h1>hello!</h1>
        <div id="root">${reactContent}</div>
        ${scriptTags}
        <script>
        window.addEventListener('DOMContentLoaded', () => {
            console.log('DSL listener in root')
        })
</script>
      </body>
    </html>
  `;
  
}

export default function (req: express.Request, res: express.Response) {
  
  const {extractCriticalToChunks, constructStyleTagsFromChunks} = createEmotionServer(cache);
  
  const reactContent = renderToString(
    <StaticRouter location={req.path}>
      <App/>
    </StaticRouter>
  )
  
  const emotionChunks = extractCriticalToChunks(reactContent);
  const emotionCss = constructStyleTagsFromChunks(emotionChunks);
  res.send(renderHtmlTemplate(reactContent, emotionCss));
  
}
