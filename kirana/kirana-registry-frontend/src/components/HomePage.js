import React from 'react';
import { Link } from 'react-router-dom';
import './home.css'; // Import the CSS file

const HomePage = () => {
  return (
    <div className="home-container">
      <header className="header">
        <h1>Kirana Register</h1>
        <p>Manage your transactions effortlessly</p>
      </header>

      <section className="content">
        <div className="card">
          <h2>Record a Transaction</h2>
          <Link to="/transaction-form">
            <button className="button">Go to Transaction Form</button>
          </Link>
        </div>

        <div className="card">
          <h2>Generate Report</h2>
          <Link to="/generate-report">
            <button className="button">Go to Report Generator</button>
          </Link>
        </div>
      </section>

      <footer className="footer">
        <p>&copy; 2024 Kirana Register. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default HomePage;
