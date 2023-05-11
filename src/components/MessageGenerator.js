import React, {useState} from 'react'
import MessageFields from './MessageFields'
import axios from 'axios'
//import '../style.css';

function MessageGenerator() {
  const [message, setMessage] = useState('');
  const [showMessage, setShowMessage] = useState(false);
  const [messageTimeoutId, setMessageTimeoutId] = useState(null);

  const [bizMsgIdr, setBizMsgIdr] = useState('');
  const [creDt, setCreDt] = useState('');
  const [msgId, setMsgId] = useState('');
  const [creDtTm, setCreDtTm] = useState('');
  const [instrId, setInstrId] = useState('');
  const [endToEndId, setEndToEndId] = useState('');
  const [txId, setTxId] = useState('');

  const generateMessage = () => {
    axios.get('http://localhost:8080/process-xml')
      .then(response => {
        setMessage(response.data.generatedMessage);
        setBizMsgIdr(response.data.receiveCT.bizMsgIdr);
        setCreDt(response.data.receiveCT.creDt);
        setMsgId(response.data.receiveCT.msgId);
        setCreDtTm(response.data.receiveCT.creDtTm);
        setInstrId(response.data.receiveCT.instrId);
        setEndToEndId(response.data.receiveCT.endToEndId);
        setTxId(response.data.receiveCT.txId);

        console.log(bizMsgIdr)
      })
      .catch(error => {
        console.error(error);
      });
  }; 

  const copyFormattedXml = () => {
    navigator.clipboard.writeText(message);

    setShowMessage(true);
    clearTimeout(messageTimeoutId);
    setMessageTimeoutId(setTimeout(() => setShowMessage(false), 1000));
  };

  const copyAsInlineXml = () => {
    axios.get('http://localhost:8080/read-as-singleLine')
    .then(response => {
      const msg = response.data; 
      navigator.clipboard.writeText(msg);
      setShowMessage(true);
      clearTimeout(messageTimeoutId);
      setMessageTimeoutId(setTimeout(() => setShowMessage(false), 1000));
    })
    .catch(error => {
      console.error(error);
    });
  };
  
  
 
  return (
    <div className="main">
      <h1>Message Generation</h1>
      <button onClick={generateMessage}>Generate</button>

      <div className="textarea-container">
        <textarea className="msg" readOnly={true} value={message} onChange={e => setMessage(e.target.value)}></textarea>
        <MessageFields
        bizMsgIdr={bizMsgIdr}
        setBizMsgIdr={setBizMsgIdr}
        creDt={creDt}
        setCreDt={setCreDt}
        msgId={msgId} 
        setMsgId={setMsgId}
        creDtTm={creDtTm}
        setCreDtTm={setCreDtTm}
        instrId={instrId}
        setInstrId={setInstrId}
        endToEndId={endToEndId}
        setEndToEndId={setEndToEndId}
        txId={txId}
        setTxId={setTxId} />
      </div>

      <button onClick={copyFormattedXml}>Copy As Formatted XML</button>
      <button onClick={copyAsInlineXml}>Copy As Inline XML</button>

      {showMessage &&
        <div className="alert">Message copied!</div>
      }

    </div> 
  )
} 

export default MessageGenerator
