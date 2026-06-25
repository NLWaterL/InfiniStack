# 要处理的扩展名；按需要增删
$extensions = @(
    ".java", ".json", ".txt", ".cfg", ".ini",
    ".lang", ".properties", ".mcmeta", ".gradle",
    ".xml", ".csv", ".js", ".py"
)

# UTF-8 BOM: EF BB BF
$bom = [byte[]](0xEF, 0xBB, 0xBF)

Get-ChildItem -Path . -Recurse -File | Where-Object {
    $extensions -contains $_.Extension.ToLowerInvariant()
} | ForEach-Object {
    $path = $_.FullName
    $bytes = [System.IO.File]::ReadAllBytes($path)

    # 只处理真正以 UTF-8 BOM 开头的文件
    if ($bytes.Length -ge 3 -and
        $bytes[0] -eq $bom[0] -and
        $bytes[1] -eq $bom[1] -and
        $bytes[2] -eq $bom[2]) {

        # 跳过前 3 个字节并原样写回；不会重新编码文本
        [System.IO.File]::WriteAllBytes(
            $path,
            $bytes[3..($bytes.Length - 1)]
        )

        Write-Host "Removed BOM: $path"
    }
}