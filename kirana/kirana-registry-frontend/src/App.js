// App.js
import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import TransactionForm from './components/TransactionForm'
import ReportGenerator from './components/ReportGenerator';
import TransactionList from './components/TransactionList';
import Login from './components/Login';


const App = () => {
  const [transactions, setTransactions] = useState([]);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<HomePage />} />
        <Route
          path="/transaction-form"
          element={<TransactionForm setTransactions={setTransactions} />}
        />
        <Route
          path="/generate-report"
          element={<ReportGenerator setTransactions={setTransactions} />}
        />
        <Route
          path="/transactions"
          element={<TransactionList transactions={transactions} />}
        />
      </Routes>
    </Router>
  );
};

export default App;
