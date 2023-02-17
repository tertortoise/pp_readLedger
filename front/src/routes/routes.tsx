import React from "react";
import {createBrowserRouter, Route} from "react-router-dom";
import type {RouteObject} from "react-router-dom";
// @ts-ignore
import {v4 as uuidV4} from 'uuid';

import Main from '../components/Main';
import Books from "../components/Books";
import Book from "../components/Book";

interface RouteObj {
  path: string;
  element: React.ReactNode;
  loadSaga?: Generator;
  children?: RouteObj[];
  index?: true;
}

export function renderRoutes(routes: RouteObj[]) {

  return routes.map(route => {
    
    return <Route key={uuidV4()} path={route.path} element={route.element}>
      {route.children?.length ? renderRoutes(route.children) : null}
    </Route>
    
  })

}

export const routes: RouteObj[] = [
  {
    path: "/",
    element: <Main />,
    children: [
      {
        path: "books",
        element: <Books />,
        children: [
          {
            path: ":id",
            element: <Book />,
          }
  
        ]
      }
    ]
  }
]


