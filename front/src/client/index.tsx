import React from 'react';
import { hydrateRoot } from 'react-dom/client';
import './index.css';
import App from '../components/App';
import {BrowserRouter} from "react-router-dom";


const container = document.getElementById('root')!;
const root = hydrateRoot(
  container,
  <BrowserRouter>
    <App  />
  </BrowserRouter>
  
);
