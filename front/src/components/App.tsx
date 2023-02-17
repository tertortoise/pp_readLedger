import CssBaseline from '@mui/material/CssBaseline';
import {ThemeProvider} from '@mui/material/styles';
import {CacheProvider} from '@emotion/react';
import createEmotionCache from '../styles/cache';
import Main from "./Main";
import theme from "../styles/theme";
import {routes, renderRoutes} from "../routes/routes";
import {Routes} from "react-router-dom";

export const cache = createEmotionCache();

export default () => {
  console.log('[App] rendered')
  return <CacheProvider value={cache}>
    <ThemeProvider theme={theme}>
      <CssBaseline/>
      <Routes>
        {renderRoutes(routes)}
      </Routes>
    </ThemeProvider>
  </CacheProvider>
  
  
}
