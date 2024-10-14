// TransactionForm.js
import React, { useState } from 'react';
import './transactionform.css'; // Import the CSS file

const TransactionForm = ({ setTransactions }) => {
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('');
  const [type, setType] = useState('credit'); // default to 'credit'
  const [showModal, setShowModal] = useState(false);

  const saveTransaction = async (e) => {
    e.preventDefault();
    const transaction = {
      amount: parseFloat(amount),
      currency: currency,
      type: type,
    };

    const response = await fetch('http://localhost:8082/api/transactions?targetCurrency=USD', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + btoa('admin:root'), // Basic Authentication
      },
      body: JSON.stringify(transaction),
    });

    if (!response.ok) {
      console.error('Failed to save transaction');
      return;
    }

    const savedTransaction = await response.json();
    setTransactions((prev) => [...prev, savedTransaction]);
    setAmount('');
    setCurrency('');

    // Show modal on success
    setShowModal(true);
  };

  const closeModal = () => setShowModal(false);

  return (
    <div className="transaction-form-container">
      <h2>Record a Transaction</h2>
      <form onSubmit={saveTransaction} className="transaction-form">
        <div className="form-group">
          <label>Amount:</label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            placeholder="Enter amount"
            required
          />
        </div>
        <div className="form-group">
          <label>Currency:</label>
          <input
            type="text"
            value={currency}
            onChange={(e) => setCurrency(e.target.value)}
            placeholder="Enter currency"
            required
          />
        </div>
        <div className="form-group">
          <label>Transaction Type:</label>
          <select value={type} onChange={(e) => setType(e.target.value)}>
            <option value="credit">Credit</option>
            <option value="debit">Debit</option>
          </select>
        </div>
        <button type="submit" className="submit-button">Save Transaction</button>
      </form>

      {/* Modal */}
      {showModal && (
        <div className="modal">
          <div className="modal-content">
            <h3>Transaction saved successfully!</h3>
            <button onClick={closeModal}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default TransactionForm;
