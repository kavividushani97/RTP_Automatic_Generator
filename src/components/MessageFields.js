import React from 'react'
//import '../style.css';

function MessageFields({
  bizMsgIdr,
  setBizMsgIdr,
  creDt,
  setCreDt,
  msgId,
  setMsgId,
  creDtTm,
  setCreDtTm,
  instrId,
  setInstrId,
  endToEndId,
  setEndToEndId,
  txId,
  setTxId,
}) {
  return (
    <div className="attributes">
      <div>
        <h3>bizMsgIdr:</h3>
        <textarea readOnly={true} value={bizMsgIdr} onChange={(e) => setBizMsgIdr(e.target.value)}></textarea>
      </div>
      <div>
        <h3>creDt:</h3>
        <textarea readOnly={true} value={creDt} onChange={(e) => setCreDt(e.target.value)}></textarea>
      </div>
      <div>
        <h3>msgId:</h3>
        <textarea readOnly={true} value={msgId} onChange={(e) => setMsgId(e.target.value)}></textarea>
      </div>
      <div>
        <h3>creDtTm:</h3>
        <textarea readOnly={true} value={creDtTm} onChange={(e) => setCreDtTm(e.target.value)}></textarea>
      </div>
      <div>
        <h3>instrId:</h3>
        <textarea readOnly={true} value={instrId} onChange={(e) => setInstrId(e.target.value)}></textarea>
      </div>
      <div>
        <h3>endToEndId:</h3>
        <textarea readOnly={true} value={endToEndId} onChange={(e) => setEndToEndId(e.target.value)}></textarea>
      </div>
      <div>
        <h3>txId:</h3>
        <textarea readOnly={true} value={txId} onChange={(e) => setTxId(e.target.value)}></textarea>
      </div>
    </div>
  )
}

export default MessageFields
 