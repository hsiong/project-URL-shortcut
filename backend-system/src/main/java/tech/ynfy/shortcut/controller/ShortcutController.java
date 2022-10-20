package tech.ynfy.shortcut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.ynfy.frame.config.repeat.RepeatSubmit;
import tech.ynfy.shortcut.service.IShortcutService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/3
 */
@RestController
public class ShortcutController {
    
    @Autowired
    private IShortcutService iShortcutService;

    @GetMapping("/redirect/{id}")
    @RepeatSubmit
    public void redirect(@PathVariable String id, HttpServletResponse response) throws IOException {
        String url = iShortcutService.getUrl(id);
        response.sendRedirect(url);
    }

//    @PostMapping("/insertRedirect")
//    @RepeatSubmit
//    @CacheEvict(cacheNames = RedisConstant.CACHE_KEY_SHORT_URL, allEntries = true)
//    public ShortcutEntity insertRedirect(@RequestBody ShortcutEntity entity) throws IOException {
//        return iShortcutService.insertRedirect(entity);
//    }

}
