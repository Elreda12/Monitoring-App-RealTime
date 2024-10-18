# Monitoring-App-RealTime
 
The goal is to implement a system for managing asynchronous and
reactive operations using Spring Boot with Webflux, thus ensuring optimal performance
even during large data loads.

The project aims to create a user-friendly user interface in React, allowing
administrators and users to clearly visualize data flows,
consult real-time statistics and easily access anomaly management.
The goal of this interface is to optimize the user experience, thus facilitating monitoring
and intervention when necessary.

![Capture d'écran 2024-10-17 215806](https://github.com/user-attachments/assets/092377ac-2fa3-42bb-94e6-20f980653e1a)

 
The illustrated architecture shows a system for collecting and processing data streams in
real time. The first step is to collect the data streams via a centralized collection
system (SI Collection). These streams come from different sources, including
MSC equipment from Nokia and Ericsson, Huawei equipment for PGWs and SGWs,
as well as OCS systems with data streams named Rec20 and Data20. The collected
streams are then routed to five Edge servers (Edge1 to Edge5), each receiving
specific streams according to their type and source. On each Edge server, Apache NiFi is used to
decode and dispatch the data streams.

Industrialization of data chain monitoring and alert orchestration
NiFi processes the data in real time, decodes it to extract the relevant
information, then distributes it to the MapR6 and MapR7 storage clusters. These clusters are used to store
and manage the processed data, allowing efficient retrieval and analysis. Thus, this
architecture allows robust and real-time management of data flows from their collection
to their storage.

![image](https://github.com/user-attachments/assets/67e3c3d7-70da-437a-9107-def990b3726a)

Project Structure
1. Data Processing Strategy with Apache Processors
NiFi
![image](https://github.com/user-attachments/assets/efde5a07-bef7-4f8d-8306-ef64d12f87d1)

![image](https://github.com/user-attachments/assets/1e5a5100-4895-4411-88ce-a9345e8851d5)

2. Presentation of the application interfaces

![image](https://github.com/user-attachments/assets/78a7f768-ca10-4733-a223-c3aa8fbc1d84)
![Capture d’écran 2024-06-05 145735](https://github.com/user-attachments/assets/a84a7cc9-0528-474e-b67e-cc9531b9fefa)

![image](https://github.com/user-attachments/assets/6e6ea51e-6210-496f-ae94-a2bbafbdfb75)



