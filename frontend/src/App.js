import { useEffect, useState } from 'react';
import axios from 'axios';
import { defaultResObject, lazyRender } from './utils';

function App() {
  const [mails, setMails] = useState(defaultResObject());

  useEffect(() => {
    const token = localStorage.getItem('token');
    axios.post('http://localhost:8080/email', {
      token: token ? token : undefined
    })
      .then((res) => {
        console.log(res);
        setMails({ ...mails, data: res.data, status: res.status });
        res.data.token && localStorage.setItem('token', res.data.token);
      });
  }, []);

  return (
    <main className="main">
      <header className="header">
        10MinuteMail
      </header>
      {mails.data && mails.data.domain && <div className="domain">{mails.data.domain}</div>}
      <div className="refresh">Refresh</div>
      {lazyRender(
        mails.data.domain ? 
          <div className="main__content">
            <div className="mail-container">
            {
              mails.data.emails.map((item) => (
                <div className="mail-container__item mail-item">
                  <div className="mail-item__header">
                    <div className="mail-item__from">{item.from}</div>
                    <div className="mail-item__date">{item.date}</div>
                  </div>
                  <div className="mail-item__subject">{item.subject}</div>
                  <div className="mail-item__message">{item.contentText}</div>
                </div>
              ))
            }
            </div>
          </div>
        : <div className="error">No free domain left :( </div>,
        mails.status
      )}
    </main>
  );
}

export default App;
