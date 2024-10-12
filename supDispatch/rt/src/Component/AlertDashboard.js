import React, { useState, useEffect } from 'react';
import '../App.css';

const AlertDashboard = () => {
    const [date, setDate] = useState('');
    const [platform, setPlatform] = useState('MSC');
    const [flow, setFlow] = useState('MSC Ericsson');
    const [alerts, setAlerts] = useState([]);
    const [error, setError] = useState('');

    const fetchAlerts = async () => {
        try {
            const response = await fetch(`/api/alerts/data?platform=${platform}&flow=${flow}&date=${date}`);
            const result = await response.json();
            setAlerts(result.slice(0, 20));  // Display only the first 20 results
        } catch (error) {
            console.error('Fetch error:', error);
            setError('Failed to fetch alerts. Please try again later.');
        }
    };

    return (
        <div className="alerts-background">
            <div className="page-content">
                <h2>Alert Dashboard</h2>
                <div>
                    <label>Date:</label>
                    <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
                </div>
                <div>
                    <label>Platform:</label>
                    <select value={platform} onChange={(e) => setPlatform(e.target.value)}>
                        <option value="MSC">MSC</option>
                        <option value="FIXE">FIXE</option>
                        <option value="OCS">OCS</option>
                        <option value="DATA">DATA</option>
                    </select>
                </div>
                <div>
                    <label>Flow:</label>
                    <input type="text" value={flow} onChange={(e) => setFlow(e.target.value)} />
                </div>
                <div>
                    <button onClick={fetchAlerts}>Fetch Alerts</button>
                </div>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <table className="table-alerts">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Platform</th>
                        <th>Flow</th>
                        <th>Alerts</th>
                    </tr>
                    </thead>
                    <tbody>
                    {alerts.map((alert, index) => (
                        <tr key={index}>
                            <td>{alert.date}</td>
                            <td>{alert.platform}</td>
                            <td>{alert.flow}</td>
                            <td>{alert.alerts.join(', ')}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AlertDashboard;
