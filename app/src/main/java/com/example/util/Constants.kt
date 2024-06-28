package com.example.util

object Constants {
    const val BASE_URL = "https://ticketing-service-test2-cluster.cinewav.com/api/graphql"
    const val EVENTS_QUERY = """
        query {
            getPublicEvents(options: { take: 10 }) {
                items {
                    id
                    title
                    priceRangeStart
                    currencyKey
                    countryKey
                    organizer {
                        companyName
                    }
                    posters {
                        id
                        sizes {
                            small {
                                location
                            }
                        }
                    }
                    performances {
                        title
                        startDate
                        endDate
                        timezone
                    }
                }
            }
        }
    """
}