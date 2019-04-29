/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.FieldReference;
import com.codingrangers.nosejob.models.MethodReference;
import com.google.common.collect.Sets;

import java.util.*;

/* Singleton to store all method references and field references.
 * Only available to the package.
 */
class ReferenceStorage {

    private Map<String, List<MethodReference>> methodReferencesByReferencing;
    private Map<String, List<MethodReference>> methodReferencesByReferred;

    private Map<String, List<FieldReference>> fieldReferencesByReferencing;
    private Map<String, List<FieldReference>> fieldReferencesByReferred;


    public ReferenceStorage() {
        methodReferencesByReferencing = new HashMap<>();
        methodReferencesByReferred = new HashMap<>();

        fieldReferencesByReferencing = new HashMap<>();
        fieldReferencesByReferred = new HashMap<>();
    }

    Iterable<MethodReference> getMethodReferenceByReferencing(String className) {
        return methodReferencesByReferencing.get(className);
    }

    Iterable<MethodReference> getMethodReferenceByReferred(String className) {
        return methodReferencesByReferred.get(className);
    }

    Iterable<MethodReference> getMethodReference(String referencingName, String referredName) {
        return intersection(getMethodReferenceByReferencing(referencingName), getMethodReferenceByReferred(referredName));
    }

    void add(MethodReference reference) {
        addToMap(methodReferencesByReferencing, reference.getReferencingClassName(), reference);
        addToMap(methodReferencesByReferred, reference.getReferredClassName(), reference);
    }

    Iterable<FieldReference> getFieldReferenceByReferencing(String className) {
        return fieldReferencesByReferencing.get(className);
    }

    Iterable<FieldReference> getFieldReferenceByReferred(String className) {
        return fieldReferencesByReferred.get(className);
    }

    Iterable<FieldReference> getFieldReference(String referencingName, String referredName) {
        return intersection(getFieldReferenceByReferencing(referencingName), getFieldReferenceByReferred(referredName));
    }

    void add(FieldReference reference) {
        addToMap(fieldReferencesByReferencing, reference.getReferencingClassName(), reference);
        addToMap(fieldReferencesByReferred, reference.getReferredClassName(), reference);
    }

    private static <T> Iterable<T> intersection(Iterable<T> first, Iterable<T> second) {
        if (first == null || second == null)
            return new ArrayList<>();
        Set<T> firstSet = Sets.newHashSet(first);

        firstSet.retainAll(Sets.newHashSet(second));

        return firstSet;
    }

    private static <T> void addToMap(Map<String, List<T>> map, String key, T element) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }

        List<T> list = map.get(key);
        list.add(element);
    }
}
