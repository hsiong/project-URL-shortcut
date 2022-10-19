package tech.ynfy.shortcut.service;

import tech.ynfy.shortcut.entity.ShortcutEntity;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/10/18
 */
public interface IShortcutService {

    /**
     * 获取跳转链接
     * @param code
     * @return
     */
    String getUrl(String id);

    /**
     * 插入重定向
     * @param entity
     */
    ShortcutEntity insertRedirect(ShortcutEntity entity);

}
