import {useEffect} from "react";

export default function Main() {
  
  useEffect(() => {
  
    
    
    fetch('http://localhost:8080/books/findAll', {
      method: 'POST'
    }).then(response => response.json()).then(json => console.log(json));
  
  }, [])
  
  return <div>
    Main
  </div>
  
}
