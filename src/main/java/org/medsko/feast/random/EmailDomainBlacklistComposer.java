package org.medsko.feast.random;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmailDomainBlacklistComposer {

    List<String> composeBlacklist(String inputFilePath) throws IOException {

        Path inputFile = Paths.get(inputFilePath);

        Set<String> allDomains = Files.readAllLines(inputFile).stream()
                .collect(Collectors.toUnmodifiableSet());

        Set<String> uniqueBaseDomains = Files.readAllLines(inputFile).stream()
                .map(this::getBaseDomain)
                .collect(Collectors.toUnmodifiableSet());

        // Only include a wildcard base domain if it matches more than 1 full domain.
        List<String> validWildcardDomains = uniqueBaseDomains.stream()
                .filter(baseDomain -> countMatchingDomains(baseDomain, allDomains) > 1)
                .collect(Collectors.toList());

        List<String> validLiteralDomains = allDomains.stream()
                .filter(domain -> validWildcardDomains.stream()
                        .noneMatch(wildcardDomain -> wildcardDomain.equals(getBaseDomain(domain)))
                ).collect(Collectors.toList());

        List<String> blacklist = validWildcardDomains.stream()
                .map(baseDomainMissingWildcard -> baseDomainMissingWildcard + ".*")
                .collect(Collectors.toList());
        blacklist.addAll(validLiteralDomains);
        return blacklist;
    }

    // TODO: dit hoort niet in deze klasse - input zou een lijst met strings moeten zijn.
    private List<String> readAllDomains(String inputFile) throws IOException {
        return Files.readAllLines(Paths.get(inputFile));
    }


    private long countMatchingDomains(String baseDomain, Set<String> allDomains) {
        return allDomains.stream()
                .filter(domain -> baseDomain.equals(getBaseDomain(domain)))
                .count();
    }

    private String getBaseDomain(String domain) {
        return domain.substring(0, domain.indexOf("."));
    }

}
