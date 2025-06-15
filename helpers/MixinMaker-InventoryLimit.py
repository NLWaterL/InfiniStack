import os
import re
import shutil
from pathlib import Path

def find_java_files_with_target_method(root_dir):
    """
    查找包含目标方法的Java文件
    """
    target_method = r'public\s+int\s+func_70297_j_\s*\(\s*\)\s*\{\s*return\s+64\s*;\s*\}'
    found_files = []
    
    for root, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                try:
                    with open(file_path, 'r', encoding='utf-8') as f:
                        content = f.read()
                        if re.search(target_method, content, re.MULTILINE | re.DOTALL):
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

def remove_target_method_from_file(file_path):
    """
    从Java文件中移除目标方法
    """
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # 更精确的正则表达式来匹配目标方法
        target_method_pattern = r'public\s+int\s+func_70297_j_\s*\(\s*\)\s*\{\s*return\s+64\s*;\s*\}'
        
        # 移除目标方法
        modified_content = re.sub(target_method_pattern, '', content, flags=re.MULTILINE | re.DOTALL)
        
        # 清理多余的空行
        modified_content = re.sub(r'\n\s*\n\s*\n', '\n\n', modified_content)
        
        # 备份原文件
        backup_path = file_path + '.backup'
        shutil.copy2(file_path, backup_path)
        print(f"已备份原文件到: {backup_path}")
        
        # 写入修改后的内容
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(modified_content)
        
        print(f"已从文件中移除目标方法: {file_path}")
        return True
        
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

def generate_mixin_file(original_file_path, class_name, package_name, script_dir):
    """
    生成Mixin文件
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
import org.spongepowered.asm.mixin.Overwrite;
import com.phasico.infinistack.helper.Configurables;

@Mixin({class_name}.class)
public abstract class Mixin{class_name} {{

    @Overwrite(remap = false)
    public int func_70297_j_() {{
        return Configurables.maxStackSize;
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

def main():
    # 获取脚本所在目录
    script_dir = os.path.dirname(os.path.abspath(__file__))
    print(f"脚本目录: {script_dir}")
    
    # 查找包含目标方法的Java文件
    print("正在查找包含目标方法的Java文件...")
    found_files = find_java_files_with_target_method(script_dir)
    
    if not found_files:
        print("未找到包含目标方法的Java文件。")
        return
    
    print(f"找到 {len(found_files)} 个文件")
    
    # 处理每个找到的文件
    for file_path in found_files:
        print(f"\n处理文件: {file_path}")
        
        # 提取类名和包名
        class_name = extract_class_name_from_file(file_path)
        package_name = extract_package_from_file(file_path)
        
        if not class_name:
            print(f"无法提取类名，跳过文件: {file_path}")
            continue
        
        print(f"类名: {class_name}")
        print(f"包名: {package_name}")
        
        # 生成Mixin文件
        mixin_file = generate_mixin_file(file_path, class_name, package_name, script_dir)
        
        if mixin_file:
            # 询问是否移除原文件中的方法
            response = input(f"是否从原文件中移除目标方法? (y/n): ").lower().strip()
            if response == 'y' or response == 'yes':
                remove_target_method_from_file(file_path)
            else:
                print("跳过移除操作")
    
    print("\n处理完成！")

if __name__ == "__main__":
    main()