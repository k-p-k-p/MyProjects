
# 1)

# from bs4 import BeautifulSoup
# import requests

# with open('D:/kamal/programs/Websites/GuessRandom/index.html') as html_file:
#     soup = BeautifulSoup(html_file,'lxml')

# # wrapper = soup.find('div',class_='wrapper')
# # paragraph_text = wrapper.p.text
# # print(paragraph_text)
# # print(soup)
# print(soup.find('div',class_='resultParas'))
# print()
# resultparas = soup.find('div',class_='resultParas')
# for paras in resultparas.find_all('p'):
#   print(paras.text)

# 2)

# from bs4 import BeautifulSoup
# import requests

# source = requests.get('https://quotes.toscrape.com/').text

# soup = BeautifulSoup(source, 'lxml')

# i = 0
# for quote in soup.find_all('span',class_='text'):
#     i+=1
#     print(i)
#     print(quote.text)
#     print()
#     print()


# 3)
# from bs4 import BeautifulSoup
# import requests

# source = requests.get('https://quotes.toscrape.com/').text #getting text from source

# soup = BeautifulSoup(source,'lxml')

# i=0
# for quote in soup.find_all('div',class_='quote'):
#     i+=1
#     print("%d%s" % (i,'.'))
#     print(quote.find('span',class_='text').text)
#     print('- '+quote.small.text)
#     print()


# 4)
# from bs4 import BeautifulSoup
# import requests

# source = requests.get('https://quotes.toscrape.com/').text #getting text from source

# soup = BeautifulSoup(source,'lxml')

# headline = soup.div.h1.a.text
# print("Heading: \n%s" % headline)
# print()

# print("1st Quote:")
# FirstQuote = soup.div.span.text
# print(FirstQuote)


# 5)

# from bs4 import BeautifulSoup
# import requests

# source = requests.get('https://www.codewithharry.com/videos/python-100-days-of-code-1/').text #getting text from source

# soup = BeautifulSoup(source,'lxml')

# vid_src = soup.find('iframe')['src']
# vid_id = vid_src.split('/')[4]
# # print(vid_id)

# yt_link = f'https://youtube.com/watch?v={vid_id}'
# print(yt_link)

#6)

from bs4 import BeautifulSoup
import requests
import re
import csv

source = requests.get('https://influencermarketinghub.com/top-vloggers/').text #getting text from the webpage

soup = BeautifulSoup(source,'lxml')

csv_file = open('influencers_scrape.csv','w')

csv_writer = csv.writer(csv_file)
csv_writer.writerow(['Top Vlogging Channels','About','Video_Link'])

names = [] 
video_links = []
abouts = []


#channel names
for influencers in soup.find_all('h2',class_=''):
    name = influencers.text
    # This regex pattern matches numbers followed by a dot and variable spaces
    pattern = r'\d+\.\s*'
    # Substitute the pattern with an empty string
    name = re.sub(pattern, '', name)
    names.append(name)
    print(name)



#video link part
for embed_vids in soup.find_all('div',class_='embed-container'):
    vid_link = embed_vids.find('div',class_='rll-youtube-player')['data-src']
    video_links.append(vid_link)
    print(vid_link)

#about part
paras = []
i = 0
for para in soup.find_all('span'):
    paras.append(para)
    # i+=1
    # print(f'{para.text}\n\n\n {i}')
print(len(paras))

abouts.append(paras[12].text)
abouts.append(paras[13].text)
abouts.append(paras[17].text)
abouts.append(paras[20].text)
abouts.append(paras[27].text)
abouts.append(paras[29].text)
abouts.append(paras[35].text)
abouts.append(paras[42].text)
abouts.append(paras[54].text)
abouts.append(paras[62].text)
abouts.append(paras[81].text)
abouts.append(paras[88].text)
abouts.append(paras[96].text)
abouts.append(paras[105].text)
abouts.append(paras[114].text)
abouts.append(paras[117].text)
print(len(abouts))
print(len(names))
print(len(video_links))

print(abouts[15])


for i in range(0,16) :
    csv_writer.writerow([names[i],abouts[i],video_links[i]])


csv_file.close()

# i = 0
# for vids in soup.find_all('iframe'):
#     i+=1
#     print(f'{i}\n {vids}')

# i = 0
# for paras in soup.find_all('p',id_=''):
#     i+=1
#     print(f'{i} \n\n {paras}')

# all_content = soup.find('div',class_='entry-content')


