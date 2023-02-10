import CssBaseline from '@mui/material/CssBaseline';
import {ThemeProvider} from '@mui/material/styles';
import {CacheProvider} from '@emotion/react';
import createEmotionCache from '../styles/cache';
import Main from "./Main";
import theme from "../styles/theme";

export const cache = createEmotionCache();

export default () => {
  
  return <CacheProvider value={cache}>
    <ThemeProvider theme={theme}>
      <CssBaseline/>
      <Main/>
    </ThemeProvider>
  </CacheProvider>
  
  
}
