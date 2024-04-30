import requests
from bs4 import BeautifulSoup


url = 'https://www.foregolfleague.com/wordlelist.htm'


headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'
}


response = requests.get(url, headers=headers)


    soup = BeautifulSoup(response.text, 'html.parser')

    words_elements = soup.find_all('td', style=lambda value: value and 'font-size: 12.0pt' in value)


    words = [word_element.get_text(strip=True) for word_element in words_elements if len(word_element.get_text(strip=True)) == 5]


    with open('wordle_words.txt', 'w') as file:
        for word in words:
            file.write(f'{word}\n')
    


else:
    print('Failed to retrieve the webpage')
