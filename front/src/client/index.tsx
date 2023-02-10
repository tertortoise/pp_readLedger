import React from 'react';
import { hydrateRoot } from 'react-dom/client';
import './index.css';
import App from '../components/App';


const container = document.getElementById('root')!;
const root = hydrateRoot(
  container,
  <App  />
);
