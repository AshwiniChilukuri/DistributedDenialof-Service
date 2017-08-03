# DistributedDenialof-Service
There are two programs one that runs on MasterBot and the other that runs on various Slave Bots.
Master Bot issues various commands to Slave Bots. 
Commands include

i. list
- will list all the slaves connected to the master in the format SlaveHostName IPAddress SourcePortNumber RegistrationDate
ii. Connect IPAddress/HostnameofSlave/all TargetHostName/TargetIPAddress TargetPortnumber Number ofConnections
-Establishes number of connections to the target host
iii. Disconnect IPAddress/HostnameofSlave/all TargetHostName/TargetIPAddress TargetPortnumber
- Closes all the connections to the given Target
iv. connect IPAddress/HostNameOfSlave/all TargetHostName/IPAddress TargetPortNumber NumberOfConnections keepalive
- The connections between the slaves and the target are kept alive
v. connect IPAddress/HostNameOfSlave/all TargetHostName/IPAddress TargetPortNumber NumberOfConnections url=[path]
- Slaves connects to the URL specified
vi. ipscan IPAddress/HostNameOfSlave/all IPAddressRange
- returns the IPAddresses that are reachable in a given range
vii. tcpportscan IPAddress/HostNameOfSlave/all TargetHostName/IPAddress TargetPortNumberRange
- returns the portnumbers of the target that can be connected in the given range
viii. geoipscan IPAddress/HostNameOfSlave/all IPAddressRange
- returns the geographic locations of IPAddresses that are reachable in a given range
