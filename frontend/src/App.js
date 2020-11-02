import { useEffect, useState } from 'react';
import axios from 'axios';
import { defaultResObject, lazyRender, TIMEOUT } from './utils';

function App() {
  const [mails, setMails] = useState(defaultResObject());

  const emailRequest = () => {
    const token = localStorage.getItem('token');
    axios.post('http://localhost:8080/email', {
      token: token ? token : undefined
    })
      .then((res) => {
        setMails({ ...mails, data: res.data, status: res.status });
        if (res.data.token) {
          localStorage.setItem('token', res.data.token);
          setTimeout(() => localStorage.removeItem('token'), TIMEOUT)
        }
      });
    };

  useEffect(() => {
    emailRequest();
  }, []);

  const refresh = () => {
    setMails(defaultResObject());
    emailRequest();
  };

  return (
    <main className="main">
      <header className="header">
        10MinuteMail
      </header>
      <div className="refresh" onClick={refresh}>Refresh</div>
      {mails.data && mails.data.domain && <div className="domain">{mails.data.domain}</div>}
      <div className="main__content">
        {lazyRender(
          mails.data.domain ? 
              <div className="mail-container">
              {
                mails.data.emails.map((item) => (
                  <div className="mail-container__item mail-item">
                    <div className="mail-item__header">
                      <div className="mail-item__from">{item.from}</div>
                      <div className="mail-item__date">{item.date}</div>
                    </div>
                    <div className="mail-item__subject">{item.subject}</div>
                    <div className="mail-item__message">{item.contentType === 'multipart' 
                    ? '[MULTIPART DATA]' : item.contentText}</div>
                  </div>
                ))
              }
              </div>
          : <div className="error">No free domain left :( </div>,
          mails.status
        )}
      </div>
    </main>
  );
}

export default App;
