package com.stewart.wilson.memento.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.stewart.wilson.memento.service.request.AddPinRequest;
import com.stewart.wilson.memento.service.request.CommentOnPinRequest;
import com.stewart.wilson.memento.service.request.DeletePinRequest;
import com.stewart.wilson.memento.service.request.FollowUserRequest;
import com.stewart.wilson.memento.service.request.GetAllPinsByUserRequest;
import com.stewart.wilson.memento.service.request.GetAllPinsWithinRangeRequest;
import com.stewart.wilson.memento.service.request.LikePinRequest;
import com.stewart.wilson.memento.service.request.UpdatePinRequest;
import com.stewart.wilson.memento.service.response.AddPinResponse;
import com.stewart.wilson.memento.service.response.CommentOnPinResponse;
import com.stewart.wilson.memento.service.response.DeletePinResponse;
import com.stewart.wilson.memento.service.response.FollowUserResponse;
import com.stewart.wilson.memento.service.response.GetAllPinsByUserResponse;
import com.stewart.wilson.memento.service.response.GetAllPinsWithinRangeResponse;
import com.stewart.wilson.memento.service.response.LikePinResponse;
import com.stewart.wilson.memento.service.response.UpdatePinResponse;

@Consumes("application/json")
@Produces("application/json")
public interface MementoService
{
	@POST
	@Path("/addPin/")
	public AddPinResponse addPin(AddPinRequest request);
	
	@POST
	@Path("/updatePin/")
	public UpdatePinResponse updatePin(UpdatePinRequest request);
	
	@POST
	@Path("/deletePin/")
	public DeletePinResponse deletePin(DeletePinRequest request);
	
	@POST
	@Path("/getAllPinsByUser/")
	public GetAllPinsByUserResponse getAllPinsByUser(GetAllPinsByUserRequest request);
	
	@POST
	@Path("/getAllPinsWithinRange/")
	public GetAllPinsWithinRangeResponse getAllPinsWithinRange(GetAllPinsWithinRangeRequest request);
	
	@POST
	@Path("/likePin/")
	public LikePinResponse likePin(LikePinRequest request);
	
	@POST
	@Path("/commentOnPin/")
	public CommentOnPinResponse commentOnPin(CommentOnPinRequest request);
	
	@POST
	@Path("/followUser/")
	public FollowUserResponse followUser(FollowUserRequest request);
	
	

}
