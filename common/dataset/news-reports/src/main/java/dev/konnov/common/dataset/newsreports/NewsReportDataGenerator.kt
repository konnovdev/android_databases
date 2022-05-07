package dev.konnov.common.dataset.newsreports

/**
 * Generating different articles
 * for now we only have 7 unique itmes
 */

object NewsReportDataGenerator {

    fun getEntities(size: Int): List<NewsReport> {
        val entities = mutableListOf<NewsReport>()

        for (i in 1..size) {
            entities.add(generateNewsReport(i))
        }

        return entities
    }

    private fun generateNewsReport(index: Int): NewsReport =
        NewsReport(generateTitle(index), generateDescription(index))

    private fun generateTitle(index: Int): String =
        when {
            index % 9 == 0 -> {
                "Morning in Las Vegas"
            }
            index % 8 == 0 -> {
                "Evening in San Francisco"
            }
            index % 7 == 0 -> {
                "Learning from the event"
            }
            index % 6 == 0 -> {
                "Nothing is lost"
            }
            index % 5 == 0 -> {
                "The more the better"
            }
            index % 3 == 0 -> {
                "Exploration in space"
            }
            else -> {
                "Strange things"
            }
        }
}

private fun generateDescription(index: Int) =
    when {
        index % 9 == 0 -> {
            "The Rentwertshausen–Römhild railway was a single-tracked branch line in the state of " +
                    "Thuringia in central Germany. It was just under 11 kilometres long and ran from" +
                    " Rentwertshausen to Römhild in the south Thuringian region of Grabfeld. Due to " +
                    "its proximity to the Gleichberge mountains, the line was also known as the " +
                    "Gleichbergbahn (Gleichberg railway). It was dismantled in 1973."
        }
        index % 8 == 0 -> {
            "Fabien Vanasse dit Vertefeuille (November 6, 1850 – December 3, 1936) was a journalist," +
                    " lawyer and political figure in Quebec, Canada. He represented Yamaska in the " +
                    "House of Commons of Canada from 1879 to 1891 as a Conservative Party of Canada member."
        }
        index % 7 == 0 -> {
            "Lucia A. Reisch is a behavioural economist and social scientist by training and the " +
                    "El-Erian Professor of Behavioural Economics and Policy at the University of " +
                    "Cambridge since September 2021.[1] Since April 2022 the Professorship is " +
                    "located at the Cambridge Judge Business School. Before joining Cambridge, " +
                    "she was a Professor at Copenhagen Business School (CBS).[1] She also holds an " +
                    "honorary Leibniz Professorship from Leibniz Institute for Prevention Research" +
                    " in Bremen as well as a Guest Professorship from Zeppelin University, Friedrichshafen."
        }
        index % 6 == 0 -> {
            "Half a House is a 1976 American comedy film directed by Brice Mack, produced by Lenke " +
                    "Romanszky and released theatrically in the U.S. by Rampart Releasing. It stars" +
                    " Anthony Eisley and Pat Delaney as a separated married couple who divide up " +
                    "living space when they must share their house for three months.[1] The film" +
                    " was also released as House Divided."
        }
        index % 5 == 0 -> {
            "Atzara (Sardinian: Atzàra) is a comune (municipality) in the Province of Nuoro" +
                    " in the Italian region Sardinia, located about 90 kilometres (56 mi)" +
                    " north of Cagliari and about 45 kilometres (28 mi) southwest of Nuoro."
        }
        index % 3 == 0 -> {
            "Blood & Chemistry is the debut full-length studio album by the British alternative " +
                    "rock band Arcane Roots, released in 2013 on Play It Again Sam"
        }
        else -> {
            "Berthe Bady (1872–1921) was a French actress of Belgian origin. She was the companion " +
                    "of Lugné-Poe and Henry Bataille. The fortunes she had won as an actress were " +
                    "devoted to her household with Bataille. Berthe died in isolation at Jouy-sur-Eure."
        }
    }
