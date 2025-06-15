import os
import re
import shutil
from pathlib import Path

def find_java_files_with_nextint(root_dir):
    """
    查找包含 nextInt(21) + 10 的Java文件
    """
    target_pattern = r'nextInt\s*\(\s*21\s*\)\s*\+\s*10'
    found_files = []
    
    for root, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                try:
                    with open(file_path, 'r', encoding='utf-8') as f:
                        content = f.read()
                        # 使用正则表达式查找目标模式
                        if re.search(target_pattern, content, re.MULTILINE):
                            found_files.append(file_path)
                            print(f"找到目标文件: {file_path}")
                except Exception as e:
                    print(f"读取文件 {file_path} 时出错: {e}")
    
    return found_files

def extract_class_name_from_file(file_path):
    """
    从Java文件中提取类名
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
            # 查找public class声明
            class_match = re.search(r'public\s+class\s+(\w+)', content)
            if class_match:
                return class_match.group(1)
            
            # 如果没找到public class，尝试查找普通class
            class_match = re.search(r'class\s+(\w+)', content)
            if class_match:
                return class_match.group(1)
                
    except Exception as e:
        print(f"提取类名时出错: {e}")
    
    return None

def extract_package_from_file(file_path):
    """
    从Java文件中提取包名
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
            # 查找package声明
            package_match = re.search(r'package\s+([^;]+);', content)
            if package_match:
                return package_match.group(1).strip()
    except Exception as e:
        print(f"提取包名时出错: {e}")
    
    return None

def remove_nextint_from_file(file_path):
    """
    从Java文件中移除或注释掉包含nextInt(21) + 10的行
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        # 查找并处理包含目标模式的行
        target_pattern = r'nextInt\s*\(\s*21\s*\)\s*\+\s*10'
        modified_lines = []
        changes_made = False
        
        for line in lines:
            if re.search(target_pattern, line):
                # 注释掉这一行而不是完全删除
                commented_line = "        // " + line.strip() + " // Commented by mixin processor\n"
                modified_lines.append(commented_line)
                changes_made = True
                print(f"已注释行: {line.strip()}")
            else:
                modified_lines.append(line)
        
        if changes_made:
            # 备份原文件
            backup_path = file_path + '.backup'
            shutil.copy2(file_path, backup_path)
            print(f"已备份原文件到: {backup_path}")
            
            # 写入修改后的内容
            with open(file_path, 'w', encoding='utf-8') as f:
                f.writelines(modified_lines)
            
            print(f"已处理文件: {file_path}")
            return True
        else:
            print(f"文件中未找到需要处理的内容: {file_path}")
            return False
        
    except Exception as e:
        print(f"处理文件 {file_path} 时出错: {e}")
        return False

def get_relative_path(script_dir, file_path):
    """
    获取相对于脚本目录的路径
    """
    try:
        return os.path.relpath(file_path, script_dir)
    except Exception:
        return file_path

def generate_nextint_mixin_file(original_file_path, class_name, package_name, script_dir):
    """
    生成NextInt Mixin文件
    """
    # 获取相对路径
    relative_path = get_relative_path(script_dir, original_file_path)
    
    # 构建import路径
    if package_name:
        import_path = f"{package_name}.{class_name}"
    else:
        import_path = class_name
    
    # Mixin文件内容模板
    mixin_content = f"""package com.phasico.infinistack.mixins.manametal;

import {import_path}; // {relative_path}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin({class_name}.class)
public abstract class Mixin{class_name} {{

    @Redirect(
            method = "func_149749_a",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I"
            ), remap = false
    )
    private int noStackSplit(Random random, int bound) {{
        if (bound == 21) {{
            return Integer.MAX_VALUE - 10;
        }}
        return random.nextInt(bound);
    }}

}}"""

    # 创建mixins目录（如果不存在）
    mixins_dir = os.path.join(script_dir, 'mixins')
    os.makedirs(mixins_dir, exist_ok=True)
    
    # 生成Mixin文件路径
    mixin_file_path = os.path.join(mixins_dir, f"Mixin{class_name}.java")
    
    try:
        with open(mixin_file_path, 'w', encoding='utf-8') as f:
            f.write(mixin_content)
        print(f"已生成Mixin文件: {mixin_file_path}")
        return mixin_file_path
    except Exception as e:
        print(f"生成Mixin文件时出错: {e}")
        return None

def show_file_context(file_path):
    """
    显示文件中包含目标模式的上下文
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        target_pattern = r'nextInt\s*\(\s*21\s*\)\s*\+\s*10'
        
        print(f"\n文件内容预览: {file_path}")
        print("-" * 50)
        
        for i, line in enumerate(lines):
            if re.search(target_pattern, line):
                # 显示匹配行及其上下文（前后2行）
                start = max(0, i - 2)
                end = min(len(lines), i + 3)
                
                for j in range(start, end):
                    prefix = ">>> " if j == i else "    "
                    print(f"{prefix}{j+1:3d}: {lines[j].rstrip()}")
                print("-" * 50)
                
    except Exception as e:
        print(f"显示文件内容时出错: {e}")

def main():
    # 获取脚本所在目录
    script_dir = os.path.dirname(os.path.abspath(__file__))
    print(f"脚本目录: {script_dir}")
    
    # 查找包含nextInt(21) + 10的Java文件
    print("正在查找包含 nextInt(21) + 10 的Java文件...")
    found_files = find_java_files_with_nextint(script_dir)
    
    if not found_files:
        print("未找到包含 nextInt(21) + 10 的Java文件。")
        return
    
    print(f"找到 {len(found_files)} 个文件")
    
    # 处理每个找到的文件
    for file_path in found_files:
        print(f"\n处理文件: {file_path}")
        
        # 显示文件中匹配的内容上下文
        show_file_context(file_path)
        
        # 提取类名和包名
        class_name = extract_class_name_from_file(file_path)
        package_name = extract_package_from_file(file_path)
        
        if not class_name:
            print(f"无法提取类名，跳过文件: {file_path}")
            continue
        
        print(f"类名: {class_name}")
        print(f"包名: {package_name}")
        
        # 生成Mixin文件
        mixin_file = generate_nextint_mixin_file(file_path, class_name, package_name, script_dir)
        
        if mixin_file:
            # 询问是否处理原文件
            response = input(f"是否注释原文件中的 nextInt(21) + 10 相关代码? (y/n): ").lower().strip()
            if response == 'y' or response == 'yes':
                remove_nextint_from_file(file_path)
            else:
                print("跳过原文件处理")
    
    print("\n处理完成！")

if __name__ == "__main__":
    main()