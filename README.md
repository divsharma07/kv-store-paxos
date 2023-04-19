## Project 4
### About Project
This project involves implementation of a Key Value store, that constitutes a multithreaded server that
exposes PUT, GET and DELETE functionalities using RPC, specifically gRPC in this implementation. The server also provides consistency guarantees while being replicable across multiple instances.
The previous iteration of this project implemented the Two Phase Protocol but this one implements the Paxos Algorithm for consensus. More about this in the assignment overview.

### How to build the project?
The project uses Maven to orchestrate its building process. The project can be either built using an IDE like Intellij IDEA which provides Maven extensions.

If Maven is installed and is in the local path, we can use this command to create the jar and proto target files again:

mvn install

or

mvn clean install

Both of the processes above lead to 2 jar files as output in the target/ folder. Jars are names server.jar and client.jar. The proto file in the src/java/proto folder is also build by a maven plugin.

### How to run the project?
After building and getting the jars, run the following commands:

### APIs Exposed:
The following APIs are defined in the Proto folder:
1) keyvalue.proto: Implements the following APIs that handle the core functionality, i.e. the KeyValue Store.
      * put: put a value into the given key
      * get: get a value based on the provided key
      * delete: delete a key
2) paxos.proto: 
      * promise: request send by proposer, to all the acceptors, 
   

#### Server Command
java -jar ./server.jar <portNumber> <serversFileName>

Where portNumber is the port that you want to assign to the current server and the serversFileName has the addresses of all the servers involved.
Run the following command in separate terminals. Make sure to stick with the port numbers, because these are the ports in the provided liveServers.txt:

`java -jar ./server.jar 12345 liveServers.txt`
`java -jar ./server.jar 12346 liveServers.txt`
`java -jar ./server.jar 12347 liveServers.txt`
`java -jar ./server.jar 12348 liveServers.txt`
`java -jar ./server.jar 12349 liveServers.txt`

Where port number is the port on which the server should bind to.

** Note: Make sure that the serversFileName file is at the same location as the jars. This contains all the servers that need to be started up before the system is up and running and received the first request.**

#### Client Command
java -jar ./client.jar <serverAddress> <portNumber> <seedData> <seedDataFile>

Where the arguments mean the following:
1. serverAddress: The address the server is running on. This would always be localhost in our implementation since we are not hosting the server remotely.
2. portNumber: The port number that the server is running on.
3. seedData: This is a boolean value, that decides if the client uses the seed1.txt file to seed the data.
4. seedDataFile: This is the file that contains the seed data.

**Note: Make sure that the seedDataFile file, that contains the seed data is added to the same location as the jars. This file contains the seed data**

### Making requests via the client
There are two ways we can interact with the client.
1. Add more lines of instructions to the seed1.txt file and recompile. Take care of the pattern that is used in the rest of the commands. Convention goes like, 1 is used for PUT, 2 for GET and 3 for Delete.
2. Use the console to send new requests to the server. This does not require recompiling.

Note: The client keeps on running till the program is interrupted and keeps on expecting more requests. Same goes for the server.

### Executive Summary
#### Assignment Overview
The assignment has bolstered my understanding of the following concepts:
1. A deeper understanding of how RPC works and choosing amongst the myriad implementations. Eventually, for this project, gRPC was chosen partially because of the fact that I have previously used it but also because it has
   many advantages over the other options, for example, it has proto file implementations across multiple programming languages and also provides better performance.
2. The main focus of the assignment is actually the implementation of the Paxos algorithm, and it made me think of ways in which we could achieve the core goals:
    * All servers have consistent data at each time.
    * If some servers don't reply or because for some reason do not give a successful response to one of the stages the whole request still goes through if n/2 + 1 servers (or whatever the definition of majority is). It fails if the majority denies the possibility of
    * PUT and DELETE need to go through PAXOS. GET stays locked if the key that needs to be retrieved is one which is going through the Paxos Algorithm.
3. The multi-threading approach for this assignment is a bit more granular than the last. Which again is a really important part of the assignment.  Instead of locking each of the RPC methods as a whole, a new class called LockByKey is created which uses Semaphores to lock each of the keys that is going through a change. This is what the whole process looks like:
    * A new proto file is defined which sets the contract for the Paxos Algorithm.
    * Each of the servers that received an initial request to PUT/DELETE becomes the coordinator of the Paxos Algorithm.
    * The coordinator sends a prepared message to all other servers, follows the whole process of sending a new proposal id, and checks if the majority of servers respond with a promise. Each server receives this request in its promise RPC and the different situations defined by the Paxos algorithm is covered.
    * Even if one of the servers fails to return a promise, we proceed as long as a majority of servers respond. This is exactly where Paxos shines, compared to the Two-Phase Protocol that was implemented as a part of the last Project.
    * Next, if consensus is reached the accept RPC is triggered for all servers and if that succeeds with a majority, we send learn requests when the operation gets executed for all the servers involved.
    * The approach is quite performant because of the per-key locking mechanism, which will not call delays as the number of requests increases.
    * Only the acceptors fail in this situation, which is where the random failures have been implemented. To cope with these failures the proposer implements an exponential backoff timeout, because of which the acceptor received at least 3 requests with increasing timeout in between, before failing.
    * ConcurrentHashMap is used to further make sure that multiple threads are not editing the KVMap.
    * The lock that is implemented by the LockByKey class is kept common between the KVService and PaxosService, to make sure that the same key is being locked while it is either directly accessed by a client or is updated by a two-phase commit.
4. There are timeouts involved both in client calls as well server-to-server Paxos-related calls. This again is an important topic of the assignment. The Client calls and waits 15 seconds before terminating. Calls amongst servers time out after 10 seconds.


#### Technical Impression 
The project is a challenging task that makes us implement one of the most popular fault-tolerant algorithms in distributed systems. Implementing Paxos to achieve consensus and fault tolerance among replicated servers is a complex task that requires a good understanding of distributed systems concepts, such as fault tolerance, consistency, and concurrency control.
Thinking about all the roles that need to be implemented (Proposers, Acceptors, and Learners), and configuring some parts to fail could be implemented in several ways is challenging.
The current implementation just allows acceptors to fail, 10% time using a random number generator. No coordinator is used in the implementation, the server that receives a request becomes the proposer and all others mentioned in the liveServers.txt become the acceptors. The choice of not picking a central coordinator could have both advantages and disadvantages. The biggest advantage is of course that there is no single point of failure.
The disadvantage is that the concurrency control becomes more complicated which is why the LockByKey and semaphores are used. It is to be noted that leader election is also sometimes used to get the lead proposer to fix the situation of live lock but to simplify this implementation, that part has been skipped

Another potential challenge is dealing with the failure and recovery of Paxos roles. Implementing a robust failure recovery mechanism requires careful design and testing to ensure that the system can recover from failures without compromising the consistency and fault-tolerance properties. The current implementation takes care of only the case where the acceptor fails but a fool-proof implementation would require recovery of all roles.
Overall, this project provides an excellent opportunity to apply my knowledge of distributed systems and fault-tolerant algorithms to a real-world problem. The project requirements are challenging but clear, leaving room for creativity and exploration.
