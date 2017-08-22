# ChatAppClient
A simple Chat Application

![Alt Text](https://thumbs.gfycat.com/ImmaculateOblongKingbird-size_restricted.gif)

https://gfycat.com/gifs/detail/immaculateoblongkingbird

## Summary
A Desktop Chat App designed to be extensible and scalable. It's an MVP(Minimum Viable Product), it provides a group chat feature. It's connected to a Server that broadcast the message sent to other clients. The Server (https://github.com/rodolfovilaca/ChatAppServer) uses MySQL to save data(users, messages, dates etc) and keep track of the timeline. Tests were done using Digital Ocean's Ubuntu machine as back-end server and instructions of use are in github link.

## Desing Patterns

- MVC (Model View Controller): used as main design pattern to structure all data(DAO) separeted from the view(FXML) and controllers.

## How it Works

This program uses sockets to send data over the ports(Client -> Server and Server -> Client).

