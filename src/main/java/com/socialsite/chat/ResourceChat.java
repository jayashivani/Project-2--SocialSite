package com.socialsite.chat;

import org.atmosphere.annotation.Broadcast;

import org.atmosphere.annotation.Suspend;


import javax.ws.rs.Consumes;

import javax.ws.rs.GET;

import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")

public class ResourceChat {

 @Suspend(contentType = "application/json")

 @GET

 public String suspend() {

  return "";

 }

 @Broadcast(writeEntity = false)

 @POST

 @Produces("application/json")

 public com.socialsite.chat.Response broadcast(Message message) {

  return new com.socialsite.chat.Response(message.author, message.message);

 }

}