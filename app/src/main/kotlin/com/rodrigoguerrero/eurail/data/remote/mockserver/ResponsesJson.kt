package com.rodrigoguerrero.eurail.data.remote.mockserver

internal val validResponseText = """
{
    "articles": [
        { "id": "1", "title": "First Article", "summary": "Summary", "updated_date": "31 May 2025" },
        { "id": "2", "title": "Second Article", "summary": "Summary 2", "updated_date": "21 June 2025"  },
        { "id": "3", "title": "Third Article", "summary": "Summary 3", "updated_date": "1 April 2025"  }
    ]
}
""".trimIndent()

internal val validEmptyResponseText = """
{
    "articles": []
}
""".trimIndent()

internal val invalidJsonResponseText = """
{
    "articles": [
        { "id": "1", "title": "First Article", "summary": "Summary", "updated_date": "31 May 2025" },
    ]
}
""".trimIndent()

internal val clientErrorResponse = """
    {
        "errorCode": 123,
        "errorTitle": "Client error title",
        "errorMessage": "Error message to display to the user"
    }
""".trimIndent()
