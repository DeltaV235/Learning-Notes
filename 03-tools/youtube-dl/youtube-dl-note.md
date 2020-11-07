# youtube-dl

## 0x00 Github Repository Fork

[Github Repository](https://github.com/l1ving/youtube-dl)

## 0x01 Installation

```shell
sudo curl -L https://github.com/l1ving/youtube-dl/releases/latest/download/youtube-dl -o /usr/local/bin/youtube-dl
sudo chmod a+rx /usr/local/bin/youtube-dl
```

## 0x02 Usage

```shell
youtube-dl [OPTIONS] URL [URL...]
```

## 0x03 how-to-select-video-quality-from-youtube-dl

[how-to-select-video-quality-from-youtube-dl](https://askubuntu.com/questions/486297/how-to-select-video-quality-from-youtube-dl)

```shell
youtube-dl -F 'http://www.youtube.com/watch?v=P9pzm5b6FFY'
```

Here is the output:

```shell
[youtube] Setting language
[youtube] P9pzm5b6FFY: Downloading webpage
[youtube] P9pzm5b6FFY: Downloading video info webpage
[youtube] P9pzm5b6FFY: Extracting video information
[info] Available formats for P9pzm5b6FFY:
format code extension resolution  note
140         m4a       audio only  DASH audio , audio@128k (worst)
160         mp4       144p        DASH video , video only
133         mp4       240p        DASH video , video only
134         mp4       360p        DASH video , video only
135         mp4       480p        DASH video , video only
136         mp4       720p        DASH video , video only
17          3gp       176x144
36          3gp       320x240
5           flv       400x240
43          webm      640x360
18          mp4       640x360
22          mp4       1280x720    (best)
```

**The best quality is 22** so use -f 22 instead of -F to download the MP4 video with 1280x720 resolution like this:

```shell
youtube-dl -f 22 'http://www.youtube.com/watch?v=P9pzm5b6FFY'
```

**Or** optionally use the following flags to automatically download the best audio and video tracks that are available as a single file:

```shell
youtube-dl -f best 'http://www.youtube.com/watch?v=P9pzm5b6FFY'
```

```shell
youtube-dl -f 137+141 https://www.youtube.com/watch\?v\=-pxRXP3w-sQ
```
