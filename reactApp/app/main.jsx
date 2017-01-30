import React from 'react';
import ReactDOM from 'react-dom';
//import App from './App.jsx';

class App extends React.Component {
    render () {
        return <p> Hello World by ReactJs! </p>;
    }
}

ReactDOM.render(<App />, document.getElementById('app'));
