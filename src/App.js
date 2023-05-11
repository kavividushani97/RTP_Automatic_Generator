import './App.css';
import MessageGenerator from './components/MessageGenerator';
import SideBar from './components/SideBar';
import TopBar from './components/TopBar';

function App() {
  return (
    <div className="App">
      <TopBar/>
      <SideBar/>
      <MessageGenerator/>
    </div>
  );
}

export default App;
