import createCache from "@emotion/cache";

export default function() {
  
  let cssInsertionPoint = (typeof document !== 'undefined' ? document.querySelector?.('meta[name="emotion-insertion-point"]') : undefined) as HTMLElement | undefined;
  
  const cache = createCache({ key: 'mui-style', insertionPoint: cssInsertionPoint });
  
  return cache;

}
