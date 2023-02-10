import {useEffect} from "react";

export default function Main() {
  
  const clickHndlr = () => {
    console.log('I was clicked')
  }
  
  useEffect(() => {
    
    // fetch('http://localhost:8080/books/findAll', {
    //   method: 'POST'
    // }).then(response => response.json()).then(json => console.log(json));
  
  }, [])
  
  return <div>
    Main!!!
    New on!
    <button onClick={clickHndlr}>Press me</button>
  </div>
  
}
