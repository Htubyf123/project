package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

@RestController
@RequestMapping("/links")
public class LinksController {
    private final LinkService linkService;

    public LinksController(LinkService linkService) {
        this.linkService = linkService;
    }


    @GetMapping
    public ListLinksResponse getLinks(@RequestHeader long tgChatId) {
        return linkService.findAll(tgChatId);
    }

    @PostMapping
    public LinkResponse addLink(@RequestHeader long tgChatId, @RequestBody AddLinkRequest addLinkRequest) {
        //throw new InvalidParametersException("Invalid parameters");
        return linkService.addLink(tgChatId, addLinkRequest);
    }

    @DeleteMapping
    public LinkResponse deleteLink(@RequestHeader long tgChatId, @RequestBody RemoveLinkRequest removeLinkRequest) {
        //throw new InvalidParametersException("Invalid parameters");
        //throw new LinkNotFoundException("Link not found");
        return linkService.removeLink(tgChatId, removeLinkRequest);
    }
}
