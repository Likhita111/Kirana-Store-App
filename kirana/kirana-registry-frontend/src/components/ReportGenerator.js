// ReportGenerator.js
import React, { useState } from 'react';
import './generateReport.css'; // Import the CSS file

const ReportGenerator = ({ setTransactions }) => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [reportData, setReportData] = useState([]);

  const generateReport = async (e) => {
    e.preventDefault();

    const response = await fetch(
      `http://localhost:8082/api/transactions/report?startDate=${startDate}&endDate=${endDate}`,
      {
        method: 'GET',
        headers: {
          Authorization: 'Basic ' + btoa('admin:root'), // Basic Authentication
        },
      }
    );

    if (!response.ok) {
      console.error('Failed to fetch transactions');
      return;
    }

    const reportTransactions = await response.json();
    setTransactions(reportTransactions);
    setReportData(reportTransactions); // Set report data for display
  };

  return (
    <div className="report-generator-container">
      <h2>Generate Transaction Report</h2>
      <form onSubmit={generateReport} className="report-form">
        <div className="form-group">
          <label>Start Date:</label>
          <input
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>End Date:</label>
          <input
            type="date"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="submit-button">Generate Report</button>
      </form>

{reportData.length > 0 && (
  <div className="report-results">
    <h3>Report Results</h3>
    <table className="report-table">
      <thead>
        <tr>
          <th>Date</th>
          <th>Amount</th>
          <th>Currency</th>
          <th>Type</th>
        </tr>
      </thead>
      <tbody>
        {reportData.map((transaction) => (
          <tr key={transaction.id}>
            <td>{transaction.date}</td>
            <td>{transaction.amount}</td>
            <td>{transaction.currency}</td>
            <td>{transaction.type}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
)}

      
    </div>
  );
};

export default ReportGenerator;
