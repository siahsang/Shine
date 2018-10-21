import '../node_modules/bootstrap/dist/css/bootstrap.css'
import '../node_modules/font-awesome/css/font-awesome.min.css'

import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import App from './App'
import registerServiceWorker from './registerServiceWorker'
import { BrowserRouter } from 'react-router-dom'

const app = (
  <BrowserRouter>
    <App/>
  </BrowserRouter>
)

ReactDOM.render(app, document.getElementById('content'))

registerServiceWorker()
