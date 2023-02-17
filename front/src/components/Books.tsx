import {useEffect} from "react";
import {Outlet} from "react-router-dom";

export default function Books() {
  
  
  
  useEffect(() => {
  
  }, [])
  
  return <div>
    list of books
    <Outlet />
  </div>
  
}
