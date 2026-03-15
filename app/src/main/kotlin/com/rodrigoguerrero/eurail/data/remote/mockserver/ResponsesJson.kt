package com.rodrigoguerrero.eurail.data.remote.mockserver

internal val validResponseText = """
{
  "articles": [
    { "id": "1", "title": "Getting Started with the App", "summary": "Learn how to set up and start using the app.", "updated_date": "15 July 2025" },
    { "id": "2", "title": "Managing Your Account", "summary": "How to update your account information and preferences.", "updated_date": "10 July 2025" },
    { "id": "3", "title": "Resetting Your Password", "summary": "Steps to reset your password if you forget it.", "updated_date": "5 July 2025" },
    { "id": "4", "title": "Understanding Notifications", "summary": "Control when and how you receive notifications.", "updated_date": "30 June 2025" },
    { "id": "5", "title": "Updating the App", "summary": "How to update the app to the latest version.", "updated_date": "21 June 2025" },
    { "id": "6", "title": "Troubleshooting Login Issues", "summary": "Common login problems and how to fix them.", "updated_date": "10 June 2025" },
    { "id": "7", "title": "Using Search Effectively", "summary": "Tips for finding help articles quickly.", "updated_date": "2 June 2025" },
    { "id": "8", "title": "Privacy and Data Protection", "summary": "How your data is stored and protected.", "updated_date": "31 May 2025" },
    { "id": "9", "title": "Managing Saved Items", "summary": "How to save and organize important content.", "updated_date": "18 May 2025" },
    { "id": "10", "title": "Contacting Support", "summary": "Ways to get help from the support team.", "updated_date": "5 May 2025" },
    { "id": "11", "title": "Customizing App Settings", "summary": "Adjust settings to personalize your experience.", "updated_date": "20 April 2025" },
    { "id": "12", "title": "Offline Access", "summary": "How to access articles without an internet connection.", "updated_date": "1 April 2025" }
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
        "errorMessage": "The article you are trying to read doesn't exist"
    }
""".trimIndent()

internal val articleOneDetails = """
    {
        "id": 1,
        "title": "Getting Started with the App",
        "content": "# Getting Started\n\nWelcome to the app! This guide will help you get started quickly.\n\n## Steps\n\n1. Download the app.\n2. Create an account.\n3. Log in and explore the features.\n\n## Tips\n\n- Use the search bar to quickly find articles.\n- Bookmark important guides for later.\n\nLearn more at [our website](https://example.com).\n\n![Welcome Image](https://picsum.photos/600/300)",
        "updated_date": "15 July 2025"
      }
""".trimIndent()

internal val articleTwoDetails = """
    {
        "id": 2,
        "title": "Managing Your Account",
        "content": "# Managing Your Account\n\nYour account settings allow you to control your personal information.\n\n## Updating Your Profile\n\n- Go to **Settings**.\n- Tap **Profile**.\n- Edit your information.\n\n## Changing Email\n\nYou can change your email anytime in the account settings.\n\n```\nSettings -> Account -> Email\n```\n\nMake sure to verify your new email address.",
        "updated_date": "10 July 2025"
      }
""".trimIndent()

internal val articleThreeDetails = """
    {
        "id": 3,
        "title": "Resetting Your Password",
        "content": "# Resetting Your Password\n\nIf you forgot your password, you can reset it easily.\n\n## Steps\n\n1. Tap **Forgot Password** on the login screen.\n2. Enter your email address.\n3. Follow the instructions in the email.\n\n> Tip: Check your spam folder if the email does not arrive.\n\n![Reset Password](https://picsum.photos/600/301)",
        "updated_date": "5 July 2025"
      }
""".trimIndent()

internal val articleFourDetails = """
    {
        "id": 4,
        "title": "Understanding Notifications",
        "content": "# Notifications\n\nNotifications help you stay informed.\n\n## Types of Notifications\n\n- Updates about new features\n- Important account alerts\n- Helpful tips\n\n## Managing Notifications\n\nGo to **Settings > Notifications** and toggle the notifications you want to receive.\n\nYou can learn more about notifications [here](https://example.com/notifications).",
        "updated_date": "30 June 2025"
      }
""".trimIndent()

internal val articleFiveDetails = """
    {
        "id": 5,
        "title": "Updating the App",
        "content": "# Updating the App\n\nKeeping the app updated ensures you have the latest features and bug fixes.\n\n## Android\n\n1. Open the **Google Play Store**.\n2. Search for the app.\n3. Tap **Update**.\n\n## Why Updates Matter\n\n- Security improvements\n- New features\n- Performance fixes",
        "updated_date": "21 June 2025"
      }
""".trimIndent()

internal val articleSixDetails = """
    {
        "id": 6,
        "title": "Troubleshooting Login Issues",
        "content": "# Troubleshooting Login Issues\n\nHaving trouble logging in? Try the following steps.\n\n## Checklist\n\n- Make sure your internet connection works.\n- Verify your email and password.\n- Reset your password if necessary.\n\n## Still Having Problems?\n\nContact support using the help section inside the app.",
        "updated_date": "10 June 2025"
      }
""".trimIndent()

internal val articleSevenDetails = """
    {
        "id": 7,
        "title": "Using Search Effectively",
        "content": "# Using Search\n\nSearch helps you quickly find help articles.\n\n## Tips\n\n- Use keywords like *account*, *password*, or *notifications*.\n- Keep searches short and specific.\n\nExample:\n\n```\npassword reset\n```\n\nThe search results will show the most relevant articles.",
        "updated_date": "2 June 2025"
      }
""".trimIndent()

internal val articleEightDetails = """
    {
        "id": 8,
        "title": "Privacy and Data Protection",
        "content": "# Privacy and Data Protection\n\nWe take your privacy seriously.\n\n## What Data We Store\n\n- Account information\n- App preferences\n\n## What We Do NOT Store\n\n- Your passwords in plain text\n- Personal documents\n\nRead our full [Privacy Policy](https://example.com/privacy).",
        "updated_date": "31 May 2025"
      }
""".trimIndent()

internal val articleNineDetails = """
    {
        "id": 9,
        "title": "Managing Saved Items",
        "content": "# Managing Saved Items\n\nYou can save important articles for quick access.\n\n## Saving an Article\n\nTap the **bookmark icon** at the top of the article.\n\n## Viewing Saved Articles\n\nGo to **Saved** in the main menu.\n\nSaved articles are available even when you're offline.",
        "updated_date": "18 May 2025"
      }
""".trimIndent()

internal val articleTenDetails = """
    {
        "id": 10,
        "title": "Contacting Support",
        "content": "# Contacting Support\n\nIf you need help, our support team is ready to assist.\n\n## Ways to Contact Us\n\n- In-app support form\n- Email support@example.com\n\n## Before Contacting\n\nCheck the help articles first—you might find a quick solution.\n\n![Support](https://picsum.photos/600/302)",
        "updated_date": "5 May 2025"
      }
""".trimIndent()

internal val articleElevenDetails = """
    {
        "id": 11,
        "title": "Customizing App Settings",
        "content": "# Customizing App Settings\n\nThe app allows you to personalize your experience.\n\n## Available Settings\n\n- Dark mode\n- Notification preferences\n- Language selection\n\nOpen **Settings** from the main menu to configure these options.",
        "updated_date": "20 April 2025"
      }
""".trimIndent()

internal val articleTwelveDetails = """
    {
        "id": 12,
        "title": "Offline Access",
        "content": "# Offline Access\n\nYou can read previously loaded articles even without internet.\n\n## How It Works\n\n- Articles are cached locally.\n- Saved articles remain available offline.\n\n> Note: New articles require an internet connection to download.",
        "updated_date": "1 April 2025"
      }
""".trimIndent()
