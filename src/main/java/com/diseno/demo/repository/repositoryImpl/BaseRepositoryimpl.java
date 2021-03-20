package com.diseno.demo.repository.repositoryImpl;

import com.diseno.demo.common.ErrorsConstant;
import com.diseno.demo.repository.repositoryCustom.BaseRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 * date 2021-03-19 08:54
 *
 * @author Phạm Ngọc Thắng
 */
public class BaseRepositoryimpl<T> implements BaseRepositoryCustom<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRepositoryimpl.class);
    private Class<T> zClass;

    @SuppressWarnings("unchecked")
    public BaseRepositoryimpl() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * lấy data có phân trang
     *
     * @param queryStr
     * @param mapParams
     * @param pageable
     * @return
     */
    @Override
    public List<T> findAll(String queryStr, Map<String, Object> mapParams, Pageable pageable) {

        StringBuilder queryString = new StringBuilder("");
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }

        queryString.append("select * from " + table.name() + " as model where del_active=1 ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }

        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);

        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));

            }
        }
        if (pageable != null) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        logger.debug("queryString: " + queryString);
        return query.getResultList();
    }

    @Override
    public List<T> findAllWebPaging(String queryStr, Map<String, Object> mapParams, int pageNumber, int maxPageItem) {
//        this.checkPage(pageNumber);
//        this.checkMaxPageItem(maxPageItem);
        StringBuilder queryString = new StringBuilder();
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }
        queryString.append("select * from " + table.name() + " as model where del_active=1 ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }
        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);
        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));
            }
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, maxPageItem);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    @Override
    public List<T> findAllWebPagingDeleteOrNot(String queryStr, Map<String, Object> mapParams, int pageNumber, int maxPageItem, boolean delete) {
//        this.checkPage(pageNumber);
//        this.checkMaxPageItem(maxPageItem);
        StringBuilder queryString = new StringBuilder();
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }
        queryString.append("select * from " + table.name() + " as model where del_active=" + delete + " ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }
        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);
        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));
            }
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, maxPageItem);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }


    @Override
    public Long countAll(String queryStr, Map<String, Object> mapParams) {

        StringBuilder countQuery = new StringBuilder("");
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }

        countQuery.append("select count(*) from " + table.name() + " as model where del_active=1 ");
        if (StringUtils.isNotBlank(queryStr)) {
            countQuery.append(queryStr);
        }
        Query countQ = entityManager.createNativeQuery(countQuery.toString());

        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                countQ.setParameter(key, mapParams.get(key));
            }
        }
        Long totalRows = Long.parseLong(countQ.getSingleResult().toString());
        return totalRows;
    }

    @Override
    public Long countAllDeleteOrNot(String queryStr, Map<String, Object> mapParams, boolean delete) {
        StringBuilder countQuery = new StringBuilder("");
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }

        countQuery.append("select count(*) from " + table.name() + " as model where del_active=" + delete + " ");
        if (StringUtils.isNotBlank(queryStr)) {
            countQuery.append(queryStr);
        }
        Query countQ = entityManager.createNativeQuery(countQuery.toString());

        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                countQ.setParameter(key, mapParams.get(key));
            }
        }
        Long totalRows = Long.parseLong(countQ.getSingleResult().toString());
        return totalRows;
    }

    /**
     * lấy data không cần phần trang
     *
     * @param queryStr
     * @param mapParams
     * @return
     */
    @Override
    public List<T> findAllNoPaging(String queryStr, Map<String, Object> mapParams) {

        StringBuilder queryString = new StringBuilder("");

        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }

        queryString.append("select * from " + table.name() + " as model where del_active=1 ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }

        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);

        if (!CollectionUtils.isEmpty(mapParams)) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));
            }
        }
        logger.debug("queryString: " + queryString);
        return query.getResultList();
    }

    /**
     * lấy data không quan tâm đến pageNumber
     *
     * @param queryStr
     * @param mapParams
     * @param pageable
     * @return
     */
    @Override
    public List<T> findAllMobile(String queryStr, Map<String, Object> mapParams, Pageable pageable) {

        StringBuilder queryString = new StringBuilder("");
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }

        queryString.append("select * from " + table.name() + " as model where del_active=1 ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }

        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);

        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));

            }
        }
        if (pageable != null) {
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    /**
     * tìm kiếm cho mobile có phân trang theo pageNumber
     *
     * @param queryStr
     * @param mapParams
     * @param pageNumber
     * @return
     */
    @Override
    public List<T> findAllMobilePaging(String queryStr, Map<String, Object> mapParams, Integer pageNumber) {
        StringBuilder queryString = new StringBuilder("");
        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }
        queryString.append("select * from " + table.name() + " as model where del_active=1 ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }
        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);
        if (mapParams != null && !mapParams.isEmpty()) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));
            }
        }
        if (pageNumber != null) {
            if (pageNumber <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Số trang phải lớn hơn hoặc bằng 1");
            }
//            Pageable pageable = PageRequest.of(pageNumber - 1, MobileConstant.MAX_PAGE_ITEM);
//            query.setFirstResult((int) pageable.getOffset());
//            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    /**
     * lấy data có sắp xếp theo bảng con
     *
     * @param queryStr
     * @param mapParams
     * @return
     */
    @Override
    public List<T> findAllNoPagingandOrderBy(String queryStr, Map<String, Object> mapParams) {

        StringBuilder queryString = new StringBuilder("");

        Table table = null;
        if (zClass.isAnnotationPresent(Table.class)) {
            table = zClass.getAnnotation(Table.class);
        }

        queryString.append("select * from " + table.name() + " as model INNER JOIN ");
        if (StringUtils.isNotBlank(queryStr)) {
            queryString.append(queryStr);
        }

        Query query = entityManager.createNativeQuery(queryString.toString(), zClass);

        if (!CollectionUtils.isEmpty(mapParams)) {
            for (String key : mapParams.keySet()) {
                query.setParameter(key, mapParams.get(key));
            }
        }
        logger.debug("queryString: " + queryString);
        return query.getResultList();
    }

//    private void checkPage(int pageNumber) {
//        if (pageNumber <= 0) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.PAGE_NUMBER_INVALID);
//        }
//    }

//    private void checkMaxPageItem(int maxPageItem) {
//        if (maxPageItem <= 0) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.MAX_PAGE_ITEM_INVALID);
//        }
//    }
}
