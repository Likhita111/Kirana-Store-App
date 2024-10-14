// TransactionList.js
import React from 'react';
import './transactionList.css'; // Import the CSS file

const TransactionList = ({ transactions }) => {
  return (
    <div className="transaction-list-container">
      <h2>Transaction History</h2>
      {transactions.length === 0 ? (
        <p className="no-transactions">No transactions available.</p>
      ) : (
        <ul className="transaction-list">
          {transactions.map((transaction) => (
            <li key={transaction.id} className="transaction-item">
              <span className="transaction-date">{transaction.date}</span>
              <span className="transaction-details">
                {transaction.amount} {transaction.currency} ({transaction.type})
              </span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default TransactionList;
