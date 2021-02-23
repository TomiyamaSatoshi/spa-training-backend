package training.spa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.spa.api.domain.Reply;
import training.spa.api.exception.ApplicationErrorException;
import training.spa.api.service.ArticleService;

@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController extends ControllerBase {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Reply insertReply(@RequestBody @Validated Reply reply, BindingResult bindingResult) throws ApplicationErrorException{
		
		// バリデーションがあれば例外をThrowする
		validate("inserReply", bindingResult.getAllErrors());
		
		// 返信を登録する
		articleService.insertReply(reply);
		
		return reply;
	}

}
