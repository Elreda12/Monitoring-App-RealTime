import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '@fortawesome/fontawesome-free/css/all.min.css';
import '../App.css';

const platformOptions = {
    "MSC": ["MSC Ericsson", "MSC Huawei", "MSC Nokia"],
    "FIXE": ["FIXE IMS_Nokia", "FIXE ALCATEL", "FIXE FRC", "FIXE MER", "FIXE MMP", "FIXE REC", "FIXE Siemens", "FIXE TPLUS", "FIXE TSSA", "FIXE VOU"],
    "OCS": ["OCS DATA", "OCS DUMP", "OCS REC", "OCS SMS", "OCS VOU"],
    "DATA": ["Data GPRS", "Data PFS", "Data Huawei PGW", "Data Huawei PS3G", "Data SDP", "Data Huawei SGW", "Data Ericson SGSN", "Data Ericson SGW"]
};

const MonitoringDashboard = () => {
    const [date, setDate] = useState('');
    const [platform, setPlatform] = useState('MSC');
    const [flow, setFlow] = useState(platformOptions[platform][0]);
    const [data, setData] = useState([]);
    const [error, setError] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        setFlow(platformOptions[platform][0]);
    }, [platform]);

    const fetchData = async () => {
        try {
            const response = await fetch(`/api/monitoring/data?platform=${platform}&flow=${flow}&date=${date}`);
            const result = await response.json();
            setData(result.slice(0, 20));  // Display only the first 20 results
        } catch (error) {
            console.error('Fetch error:', error);
            setError('Failed to fetch data. Please try again later.');
        }
    };

    return (
        <div className="monitoring-background">
            <div className="page-content">
                <h1>Monitoring Dashboard</h1>
                <div>
                    <label>Date:</label>
                    <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
                </div>
                <div>
                    <label>Platform:</label>
                    <select value={platform} onChange={(e) => setPlatform(e.target.value)}>
                        {Object.keys(platformOptions).map((platform) => (
                            <option key={platform} value={platform}>{platform}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Flow:</label>
                    <select value={flow} onChange={(e) => setFlow(e.target.value)}>
                        {platformOptions[platform].map((flow) => (
                            <option key={flow} value={flow}>{flow}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <button onClick={fetchData}>Valider</button>
                    <button style={{ backgroundColor: 'red', color: 'white', marginLeft: '10px' }} onClick={() => navigate('/alerts')}>
                        <i className="fas fa-bell"></i> View Alerts
                    </button>
                </div>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <table className="table-monitoring">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Platform</th>
                        <th>Flow</th>
                        <th>Tickets Decoded</th>
                        <th>Tickets Sent to M6</th>
                        <th>Tickets Sent to M7</th>
                        <th>File Size Transferred to M6</th>
                        <th>File Size Transferred to M7</th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map((item, index) => (
                        <tr key={index}>
                            <td>{item.date}</td>
                            <td>{item.platform}</td>
                            <td>{item.flow}</td>
                            <td>{item["tickets.decoded"]}</td>
                            <td>{item["tickets.sent.M6"]}</td>
                            <td>{item["tickets.sent.M7"]}</td>
                            <td>{item["file.size.transferred.M6"]}</td>
                            <td>{item["file.size.transferred.M7"]}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default MonitoringDashboard;
