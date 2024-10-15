import React, { useState } from 'react'
import axios from 'axios';
import './actionArea.scss'

const ActionArea = () => {
    const [originalUrl, setOriginalUrl] = useState('');
    const [shortUrl, setShortUrl] = useState('');
    const [isShortened, setIsShortened] = useState(false);

    const handleInputChange = (e) => {
        setOriginalUrl(e.target.value);
    };

    const handleButtonClick = async () => {
        if (isShortened) {
        // Copy shortened URL to clipboard
        navigator.clipboard.writeText(shortUrl);
        alert('Shortened URL copied to clipboard!');
        } else {
        try {
            const response = await axios.post('http://localhost:8085/api/urls', {
              longUrl: originalUrl,
                username: "nn"
            });

            setShortUrl(response.data.shortUrl);
            console.log(response.data.shortUrl)
            setIsShortened(true);
        } catch (error) {
            console.error('Error shortening URL:', error);
        }
    }
  };


  return (
    <div className='action-area'>
      <div className='action-box'>
        <input 
          type="url" 
          placeholder="https://www.example.com" 
          value={originalUrl}
          onChange={handleInputChange}
        />
        <button onClick={handleButtonClick}>
          shorten
        </button>

        {isShortened? <>
            <p>short url is: </p>
            <a href={"http://localhost:8085/api/urls/"+shortUrl} target='_blank'>
            {"http://localhost:8085/api/urls/"+shortUrl}
            </a>
        </>:<></>
        }
      </div>
    </div>
  );
}

export default ActionArea;