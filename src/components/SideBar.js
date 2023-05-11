import React from 'react'
//import '../style.css';

function SideBar() {
  return (
    <>
    <div className="sidebar">
    <h2 className="msg-type">Message Type</h2>
      <a href="#">Payment Messages</a>
      <a href="#">Payment related Messages</a>
      <a href="#">Control Messages</a>
      <a href="#">System Notification Messages (SNMs)</a>
    </div>
    </>
  )
}

export default SideBar
