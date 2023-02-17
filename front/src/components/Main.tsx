import {useEffect} from "react";
import {Outlet} from "react-router-dom";

export default function Main() {
  
  console.log('[Main] rendered')
  
  const clickHndlr = () => {
    console.log('I was clicked')
  }
  
  useEffect(() => {
    console.log('[Main] useEffect');
  
    window.addEventListener('DOMContentLoaded', () => {
    
      console.log('DCL listener')
    
    })
    
    // fetch('http://localhost:8080/books/findAll', {
    //   method: 'POST'
    // }).then(response => response.json()).then(json => console.log(json));
  
  }, [])
  
  return <div>
    Main!!!
    <button onClick={clickHndlr}>Press me</button>
    <Outlet />
  </div>
  
}
