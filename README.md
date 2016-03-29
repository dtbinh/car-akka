# car-akka
Playground with Akka. TP3 CAR : Data sending in a distributed application using Akka.

## Tree

```
+-------------+-----------+-------------------------+
|   Sender    | Recipient |         Message         |
+-------------+-----------+-------------------------+
| deadLetters |   Node6   |     parent : Node5      |
|             |           |     children : none     |
+-------------+-----------+-------------------------+
| deadLetters |   Node1   |      parent : none      |
|             |           | children : Node2 Node5  |
+-------------+-----------+-------------------------+
| deadLetters |   Node3   |     parent : Node2      |
|             |           |     children : none     |
+-------------+-----------+-------------------------+
| deadLetters |   Node2   |     parent : Node1      |
|             |           | children : Node3 Node4  |
+-------------+-----------+-------------------------+
| deadLetters |   Node5   |     parent : Node1      |
|             |           |    children : Node6     |
+-------------+-----------+-------------------------+
| deadLetters |   Node4   |     parent : Node2      |
|             |           |     children : none     |
+-------------+-----------+-------------------------+
|    Node2    |   Node1   | message : "Keyser Söze" |
+-------------+-----------+-------------------------+
| deadLetters |   Node2   | message : "Keyser Söze" |
+-------------+-----------+-------------------------+
|    Node1    |   Node5   | message : "Keyser Söze" |
+-------------+-----------+-------------------------+
|    Node5    |   Node6   | message : "Keyser Söze" |
+-------------+-----------+-------------------------+
|    Node2    |   Node3   | message : "Keyser Söze" |
+-------------+-----------+-------------------------+
|    Node2    |   Node4   | message : "Keyser Söze" |
+-------------+-----------+-------------------------+
```

## Graph


```
+-------------+-----------+--------------------------------+
|   Sender    | Recipient |            Message             |
+-------------+-----------+--------------------------------+
| deadLetters |   Node3   |       neighbors : Node2        |
+-------------+-----------+--------------------------------+
| deadLetters |   Node2   | neighbors : Node1 Node3 Node4  |
+-------------+-----------+--------------------------------+
| deadLetters |   Node6   |    neighbors : Node5 Node4     |
+-------------+-----------+--------------------------------+
| deadLetters |   Node1   |    neighbors : Node2 Node5     |
+-------------+-----------+--------------------------------+
| deadLetters |   Node5   |    neighbors : Node1 Node6     |
+-------------+-----------+--------------------------------+
| deadLetters |   Node4   |    neighbors : Node2 Node6     |
+-------------+-----------+--------------------------------+
| deadLetters |   Node2   |    message : "Keyser Söze"     |
+-------------+-----------+--------------------------------+
|    Node2    |   Node1   |    message : "Keyser Söze"     |
+-------------+-----------+--------------------------------+
|    Node2    |   Node3   |    message : "Keyser Söze"     |
+-------------+-----------+--------------------------------+
|    Node2    |   Node4   |    message : "Keyser Söze"     |
+-------------+-----------+--------------------------------+
|    Node5    |   Node6   |    message : "Keyser Söze"     |
+-------------+-----------+--------------------------------+
|    Node1    |   Node5   |    message : "Keyser Söze"     |
+-------------+-----------+--------------------------------+
```
