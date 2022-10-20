package tech.ynfy.shortcut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.ynfy.frame.constant.RedisConstant;
import tech.ynfy.frame.util.ConversionUtil;
import tech.ynfy.frame.util.SerialNumberUtil;
import tech.ynfy.shortcut.entity.ShortcutEntity;
import tech.ynfy.shortcut.mapper.ShortcutMapper;
import tech.ynfy.shortcut.service.IShortcutService;

import java.util.List;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/10/18
 */
@Service
public class ShortcutServiceImpl implements IShortcutService {

    @Autowired
    private ShortcutMapper shortcutMapper;
    
    @Override
    @Cacheable(cacheNames = RedisConstant.CACHE_KEY_SHORT_URL, key = "#id")
    public String getUrl(String id) {
        ShortcutEntity shortcut = shortcutMapper.selectById(id);
        if (shortcut == null) {
            throw new IllegalArgumentException("404 NOT FOUND");
        }
        return shortcut.getUrl();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShortcutEntity insertRedirect(ShortcutEntity entity) {
        String url = entity.getUrl();
        if (url == null || url.length() == 0) {
            throw new IllegalArgumentException("url can't be null!");
        }
        LambdaQueryWrapper<ShortcutEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShortcutEntity::getUrl, url);
        List<ShortcutEntity> list = shortcutMapper.selectList(wrapper);
        if (list != null && list.size() != 0) {
            throw new IllegalArgumentException("redirect url exist!");
        }

        String id = ConversionUtil.encode10To62(SerialNumberUtil.next());
        entity.setId(id);
        shortcutMapper.insert(entity);
        return entity;
    }

}
